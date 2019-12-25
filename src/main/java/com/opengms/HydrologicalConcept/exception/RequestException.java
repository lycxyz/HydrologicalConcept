package com.opengms.HydrologicalConcept.exception;

import com.opengms.HydrologicalConcept.bean.ResponseCode;
import lombok.*;

import java.io.Serializable;
/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/19 21:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestException extends RuntimeException implements Serializable{
    ResponseCode code;
    private Exception exception;

    public RequestException(ResponseCode code) {
        this.code = code;
    }

    /**
     * 以下三个方法相当于指挥者的作用，
     * 其思想是用静态方法代替原有的构造函数，在静态方法中将构造函数的参数进行适量的封装，创造出不同参数的对象
     * @param code
     * @return
     */
    public synchronized static RequestException fail(ResponseCode code){
        return RequestException.builder()
                .code(code)
                .build();
    }

    public synchronized static RequestException fail(ResponseCode code,Exception e){
        return RequestException.builder()
                .code(code)
                .exception(e)
                .build();
    }

    private synchronized static RequestExceptionBuilder builder()
    {
        return new RequestExceptionBuilder();
    }

    /**
     * 以下是使用建造者模式对@Builder功能的实现
     * 其思想是用一个静态内部类作为建造者，使用参数同名方法为复杂对象的创造指定参数。再用一个build()方法返回该复杂对象
     *
     * 创建一个名为ThisClassBuilder的内部静态类，并具有和实体类形同的属性（称为构建器）。
     * 在构建器中：对于目标类中的所有的属性和未初始化的final字段，都会在构建器中创建对应属性。
     * 在构建器中：创建一个无参的default构造函数。
     * 在构建器中：对于实体类中的每个参数，都会对应创建类似于setter的方法，只不过方法名与该参数名相同。 并且返回值是构建器本身（便于链式调用），如上例所示。
     * 在构建器中：一个build()方法，调用此方法，就会根据设置的值进行创建实体对象。
     * 在构建器中：同时也会生成一个toString()方法。
     * 在实体类中：会创建一个builder()方法，它的目的是用来创建构建器。
     *
     */
/*    private static class RequestExceptionBuilder{
        private Integer status;
        private String msg;
        private Exception exception;

        public RequestExceptionBuilder() {
        }

        public RequestExceptionBuilder status(Integer status){
            this.status = status;
            return this;
        }
        public RequestExceptionBuilder msg(String msg){
            this.msg = msg;
            return this;
        }
        public RequestExceptionBuilder exception(Exception exception){
            this.exception = exception;
            return this;
        }

        public RequestException build(){
            RequestException exception = new RequestException();
            exception.status = this.status;
            exception.msg = this.msg;
            exception.exception = this.exception;
            return exception;
        }
    }*/
}
