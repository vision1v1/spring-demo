package IoCAnnotationConfigAutowiring.Annotation;

import IoCAnnotationConfigAutowiring.ComputeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
public @interface ComputeQulifier {
    ComputeType type();
}


