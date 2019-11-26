# issues detected with spring:

ISSUE-1 if you have a method in the rest controller annotated with @PostConstruct it will generate NPE

ISSUE-2 post construct is not being invoked eagerly, only if the bean is invoked. if that is a feature it would be good to add this to the documentation

ISSUE-3 if we have a query with pagination, it breakes - `select near line 1, column 17 [SELECT COUNT(*) select a from org.acme.model.A a join fetch a.b b left join b.c c where c.foo = ?1]`

ISSUE-4 error on global handler when we try to return a json response type `org.jboss.resteasy.core.NoMessageBodyWriterFoundFailure: Could not find MessageBodyWriter for response object of type: org.acme.ExceptionResponseDTO of media type: application/octet-stream`