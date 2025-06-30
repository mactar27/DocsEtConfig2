package sn.edu.isep.dbe.DocsEtConfig.securities;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sn.edu.isep.dbe.DocsEtConfig.entities.AuditRequest;
import sn.edu.isep.dbe.DocsEtConfig.repositories.AuditRequestRepository;

import java.io.IOException;
import java.util.Date;

@Component
public class AuditRequestFilter extends OncePerRequestFilter {
    private final AuditRequestRepository auditRequestRepository;

    public AuditRequestFilter(AuditRequestRepository auditRequestRepository) {
        this.auditRequestRepository = auditRequestRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain FilterChain) throws ServletException, IOException {

        String ipAddress = request.getRemoteAddr();
        String uri = request.getRequestURI();
        String userAgent = request.getHeader("User-Agent");
        String method = request.getMethod();
        System.out.println("IpAddress: " + ipAddress + "uri: " + uri +"method: " + method+ "userAgent");

        AuditRequest auditRequest=AuditRequest.builder()
                .uri(uri)
                .method(method)
                .ipAddress(ipAddress)
                .navigateur(userAgent)
                .dateRequest(new Date())
                .build();
        auditRequestRepository.save(auditRequest);


        //boolean enMaintenance=true;
        /*if(uri.startsWith("/dbe/")) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Audit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 style=\"color:red\">Ecole teudj na</h1>");
            out.println("</body>");
            out.println("</html>");
            out.close();

        }else {
            FilterChain.doFilter(request,response);

        }
            */
        FilterChain.doFilter(request,response);

        auditRequest.setStatus(response.getStatus());


    }

}