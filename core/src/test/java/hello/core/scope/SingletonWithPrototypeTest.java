package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototypeWithJsrProvider() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanWithJsrProvider.class, PrototypeBean.class);

        // given
        ClientBeanWithJsrProvider clientBeanWithJsrProvider1 = ac.getBean(ClientBeanWithJsrProvider.class);

        // when
        int count1 = clientBeanWithJsrProvider1.logic();

        // then
        assertThat(count1).isEqualTo(1);

        // given
        ClientBeanWithJsrProvider clientBeanWithJsrProvider2 = ac.getBean(ClientBeanWithJsrProvider.class);

        // when
        int count2 = clientBeanWithJsrProvider2.logic();

        // then
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBeanWithJsrProvider {
        private final Provider<PrototypeBean> prototypeBeanProvider;

        public ClientBeanWithJsrProvider(Provider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Test
    void singletonClientUsePrototypeWithObjectProvider() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanWithObjectProvider.class, PrototypeBean.class);

        // given
        ClientBeanWithObjectProvider clientBeanWithObjectProvider1 = ac.getBean(ClientBeanWithObjectProvider.class);

        // when
        int count1 = clientBeanWithObjectProvider1.logic();

        // then
        assertThat(count1).isEqualTo(1);

        // given
        ClientBeanWithObjectProvider clientBeanWithObjectProvider2 = ac.getBean(ClientBeanWithObjectProvider.class);

        // when
        int count2 = clientBeanWithObjectProvider2.logic();

        // then
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBeanWithObjectProvider {
        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public ClientBeanWithObjectProvider(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            this.count++;
        }

        public int getCount() {
            return this.count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destryo");
        }
    }
}
