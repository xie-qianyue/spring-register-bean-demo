# spring-register-bean-demo
A demo demonstrates spring registration of beans by many ways, including BeanFactoryPostProcessor, BeanPostProcessor, and etc.

## 简要说明
### Bean Creation
Interface PersonManager 有下面的实现：

1. PersonManagerImpl, 添加了别名 `autoInjectPersonManager`，由 Spring 容器管理
2. PersonManagerProxy，同样由 Spring 容器管理

两者的区别是，PersonManagerImpl 需要手动注入 PersonDao，其 createPerson 方法调用 personDao.createPerson。而 PersonManagerProxy 的 createPerson 方法则直接创建 Person 对象。

所以要正确实例化 PersonManagerImpl 就必须手动注入 PersonDao。

该 demo 在 PersonBeanFactoryPostProcessor 中以两种方法注入 PersonDao：

1. 使用 BeanDefinitionBuilder 创建 bean definition，然后通过 DefaultListableBeanFactory 注册 bean definition；
2. 直接通过 ConfigurableListableBeanFactory 注册单例：registerSingleton

两者创建的 bean 都由 Spring 容器托管。

### BPP
展示了 BPP 的一个用法示例，拦截原来的 bean，返回一个全新的 proxy bean。

### FactoryPostProcessor 修改 bean definition
展示了在 `PersonBeanFactoryPostProcessor` 中修改 Person bean 的示例，其中 Person 由 PersonConfig 注入。

### AOP Proxy
添加了 `PersonManagerAspect`，由 Spring 容器托管的 bean 都会被切面处理，返回的是 PROXY 对象。


