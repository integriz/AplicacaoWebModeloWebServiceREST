
package com.mmarques.filter;

import com.mmarques.security.AuthenticationService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class RestAutenticationFilter implements javax.servlet.Filter{
    
    public static final String AUTENTICATION_HEADER = "Authorization";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        if(request instanceof HttpServletRequest){
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            //Pega o caveçalho de autenticação
            String authCredentials = httpServletRequest.getHeader(AUTENTICATION_HEADER);
            AuthenticationService authenticationService = new AuthenticationService();
            //verifica a autenticação com as credenciais recebidas no header
            boolean authenticationStatus = authenticationService.authenticate(authCredentials);
            
            //Se autenticado
            if(authenticationStatus){
                //o fitro libera a execução do request e do response
                filter.doFilter(request,response);
            }else{
                if(response instanceof HttpServletResponse){
                    //cria um obj httpServletResponse
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    //atribui o status 401 - não autorizado.
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    //escreve a mensagem como retorno a quem chsmou, envia e fecha
                    httpServletResponse.getWriter().write("Acesso ao serviço não autorizado!");
                    httpServletResponse.getWriter().flush();
                    httpServletResponse.getWriter().close();
                }
            }
        }
    }

    @Override
    public void destroy() {
    }
    
}
