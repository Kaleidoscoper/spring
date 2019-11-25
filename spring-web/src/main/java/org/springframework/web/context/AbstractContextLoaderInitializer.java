/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.lang.Nullable;
import org.springframework.web.WebApplicationInitializer;

/**
 * Convenient base class for {@link WebApplicationInitializer} implementations
 * that register a {@link ContextLoaderListener} in the servlet context.
 *
 * <p>The only method required to be implemented by subclasses is
 * {@link #createRootApplicationContext()}, which gets invoked from
 * {@link #registerContextLoaderListener(ServletContext)}.
 *
 * @author Arjen Poutsma
 * @author Chris Beams
 * @author Juergen Hoeller
 * @since 3.2
 */
public abstract class AbstractContextLoaderInitializer implements WebApplicationInitializer {

	/** Logger available to subclasses. */
	protected final Log logger = LogFactory.getLog(getClass());


	@Override //Mark
	public void onStartup(ServletContext servletContext) throws ServletException {
		registerContextLoaderListener(servletContext);
	}

	/**方法实现注册我们的上下文监听对象
	 * Register a {@link ContextLoaderListener} against the given servlet context. The
	 * {@code ContextLoaderListener} is initialized with the application context returned
	 * from the {@link #createRootApplicationContext()} template method.
	 * @param servletContext the servlet context to register the listener against
	 */
	//Mark
	protected void registerContextLoaderListener(ServletContext servletContext) {
		//创建我们的根的上下文环境WebApplicationContext(AnnotationConfigWebApplicationContext,根容器)对象
		//但是是由他的子类AbstractAnnotationConfigDispatcherServletInitializer实现
		WebApplicationContext rootAppContext = createRootApplicationContext();
		//创建的WebApplicationContext不为空
		if (rootAppContext != null) {
			/**
			 * 	   <listener>
			 *         <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
			 *     </listener>
			 *     <context-param>
			 *         <param-name>contextConfigLocation</param-name>
			 *         <param-value>classpath:applicationContext.xml</param-value><!--spring.xml -->
			 *     </context-param>
			 *  创建一个监听器对象
			 */
			/**
			 * 这是注解配置会进入的逻辑，调用ContextLoaderListener有参构造器，传入一个根容器，xml版本调用了无参构造器
			 * 所以ContextLoader:283会额外创建根容器
			 */
			ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
			/**
			 * 1、获取根应用的getRootApplicationContextInitializers(就是bean?)
			 * 2、把初始化器设置到监听器中
			 */
			listener.setContextInitializers(getRootApplicationContextInitializers());
			//把监听器加入到我们的上下文中
			servletContext.addListener(listener);
		}
		else {
			logger.debug("No ContextLoaderListener registered, as " +
					"createRootApplicationContext() did not return an application context");
		}
	}

	/**
	 * Create the "<strong>root</strong>" application context to be provided to the
	 * {@code ContextLoaderListener}.
	 * <p>The returned context is delegated to
	 * {@link ContextLoaderListener#ContextLoaderListener(WebApplicationContext)} and will
	 * be established as the parent context for any {@code DispatcherServlet} application
	 * contexts. As such, it typically contains middle-tier services, data sources, etc.
	 * @return the root application context, or {@code null} if a root context is not
	 * desired
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer
	 * //Mark
	 * 抽象方法创建我们的根容器，但是是由他的子类AbstractAnnotationConfigDispatcherServletInitializer实现
	 */
	@Nullable
	protected abstract WebApplicationContext createRootApplicationContext();

	/**
	 * Specify application context initializers to be applied to the root application
	 * context that the {@code ContextLoaderListener} is being created with.
	 * @since 4.2
	 * @see #createRootApplicationContext()
	 * @see ContextLoaderListener#setContextInitializers
	 */
	@Nullable
	protected ApplicationContextInitializer<?>[] getRootApplicationContextInitializers() {
		return null;
	}

}
