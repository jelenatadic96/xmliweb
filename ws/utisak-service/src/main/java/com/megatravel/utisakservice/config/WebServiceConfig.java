package com.megatravel.utisakservice.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/services/*");
    }

    @Bean(name = "utisci")
    public DefaultWsdl11Definition defaultWsdl11DefinitionUtisci(XsdSchema utisciSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UtisciPort");
        wsdl11Definition.setLocationUri("/services");
        wsdl11Definition.setTargetNamespace("http://www.megatravel.com/utisakservice/soap/dto");
        wsdl11Definition.setSchema(utisciSchema);
        return wsdl11Definition;
    }

    @Bean(name = "utisciSchema")
    public XsdSchema utisciSchema() {
        return new SimpleXsdSchema(new ClassPathResource("utisci.xsd"));
    }
	
}
