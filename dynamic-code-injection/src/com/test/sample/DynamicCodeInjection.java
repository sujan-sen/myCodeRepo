package com.test.sample;

//import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicCodeInjection {

	public static void main(String args[]) {
		URLClassLoader urlCl = null;
		try {

			ClassLoader loader = DynamicCodeInjection.class.getClassLoader();
			URL path = loader.getResource("com/test/sample/DynamicCodeInjection.class");

			// Loading from an external path
			// File file = new File("/Users/sujankumarsen/Documents/");
			// urlCl= new URLClassLoader(new URL[] { file.toURI().toURL() });

			// Loading from class path
			urlCl = new URLClassLoader(new URL[] { path });
			Class<?> preLoadedClass = urlCl.loadClass("com.test.sample.PreCode");
			Object newInstance = preLoadedClass.newInstance();
			Method meth = preLoadedClass.getMethod("testMethod", null);
			meth.invoke(newInstance, null);

			System.out.println("Hello World!!! This is main code");

			Class<?> postLoadedClass = urlCl.loadClass("com.test.sample.PostCode");
			Object newInstance1 = postLoadedClass.newInstance();
			Method meth1 = postLoadedClass.getMethod("testMethod", null);
			meth1.invoke(newInstance1, null);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		} catch (InstantiationException e) {
			System.err.println(e);
		} catch (IllegalAccessException e) {
			System.err.println(e);
		} catch (NoSuchMethodException e) {
			System.err.println(e);
		} catch (SecurityException e) {
			System.err.println(e);
		} catch (IllegalArgumentException e) {
			System.err.println(e);
		} catch (InvocationTargetException e) {
			System.err.println(e);
		}
		// } catch (MalformedURLException e) {
		// System.err.println(e);
		// }finally {
		if (urlCl != null) {
			try {
				urlCl.close();
			} catch (IOException e1) {
				System.err.println(e1);
			}
		}
	}

}
