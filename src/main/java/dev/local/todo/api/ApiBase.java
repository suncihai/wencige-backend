package dev.local.todo.api;

import io.opencensus.common.Scope;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import io.opencensus.trace.samplers.Samplers;
import net.sf.json.JSONException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.nio.cs.Surrogate;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class ApiBase {
    private static final Tracer tracer = Tracing.getTracer();
    static private Logger log = LoggerFactory.getLogger(ApiBase.class);

    private HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        if(ra!=null && ra instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
            return request;
        }else {
            return null;
        }
    }

    public ApiResponse run() {
        String subMsg;
        ApiCode.CodeMessage code;
        long startTime;

        try {
            validate();
        } catch (Exception e) {
            subMsg = ""; //= e.getMessage();
            code = ApiCode.Common.FAILURE;
            return ApiResponse.createFailure(code);
        }

        try {
            startTime = System.currentTimeMillis();
            ApiResponse data ;

            HttpServletRequest request = getRequest();
            if(request!=null) {
                try (Scope ss = tracer.spanBuilder(request.getRequestURI()).setSampler(Samplers.alwaysSample()).startScopedSpan()) {
                    data = process();
                }
            }else {
                log.error("get_request_from_RequestContextHolder_failed");
                data = process();
            }

            data.setElapseMills(System.currentTimeMillis() - startTime);
            return data;

        } catch (Exception e) {
            code = ApiCode.Common.FAILURE;
        }

        return ApiResponse.createFailure(code);
    }

    protected abstract void validate() throws Exception;

    protected abstract ApiResponse process() throws Exception;
}
