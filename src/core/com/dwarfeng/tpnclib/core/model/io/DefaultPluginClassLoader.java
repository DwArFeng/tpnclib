package com.dwarfeng.tpnclib.core.model.io;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * 默认的插件类加载器。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class DefaultPluginClassLoader extends URLClassLoader implements PluginClassLoader {

	public DefaultPluginClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
		super(urls, parent, factory);
		// TODO Auto-generated constructor stub
	}

	public DefaultPluginClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
		// TODO Auto-generated constructor stub
	}

	public DefaultPluginClassLoader(URL[] urls) {
		super(urls);
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addURL(URL url) {
		super.addURL(url);
	}

}
