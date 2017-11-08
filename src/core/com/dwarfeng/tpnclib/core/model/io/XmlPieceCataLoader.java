package com.dwarfeng.tpnclib.core.model.io;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.dwarfeng.dutil.basic.cna.model.ListModel;
import com.dwarfeng.dutil.basic.io.LoadFailedException;
import com.dwarfeng.dutil.basic.io.StreamLoader;
import com.dwarfeng.tpnclib.core.model.eum.ToolkitLevel;
import com.dwarfeng.tpnclib.core.model.struct.LeveledToolkit;
import com.dwarfeng.tpnclib.core.model.struct.PieceCata;
import com.dwarfeng.tpnclib.core.model.struct.Toolkit;

/**
 * 
 * @author  DwArFeng
 * @since 0.0.1-alpha
 */
public final class XmlPieceCataLoader extends StreamLoader<ListModel<PieceCata>> {

	private final PluginClassLoader classLoader;
	private final Toolkit standardToolkit;

	private boolean readFlag = false;

	public XmlPieceCataLoader(InputStream in, PluginClassLoader classLoader, Toolkit standardToolkit) {
		super(in);
		Objects.requireNonNull(classLoader, "入口参数 classLoader 不能为 null。");
		Objects.requireNonNull(standardToolkit, "入口参数 standardToolkit 不能为 null。");

		this.classLoader = classLoader;
		this.standardToolkit = standardToolkit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load(ListModel<PieceCata> pieceCataModel) throws LoadFailedException, IllegalStateException {
		if (readFlag)
			throw new IllegalStateException("该读取器已经使用过了");

		Objects.requireNonNull(pieceCataModel, "入口参数 fileProcessorModel 不能为 null。");

		try {
			readFlag = true;

			SAXReader reader = new SAXReader();
			Element root = reader.read(in).getRootElement();

			/*
			 * 根据 dom4j 的相关说明，此处转换是安全的。
			 */
			@SuppressWarnings("unchecked")
			List<Element> clazz = (List<Element>) root.elements("class");

			for (Element claz : clazz) {
				String nameString = claz.attributeValue("name");

				if (Objects.isNull(nameString)) {
					throw new IllegalArgumentException("不存在的xml字段 name。");
				}

				Method method = classLoader.loadClass(nameString).getMethod("newInstance", Toolkit.class);

				Object object = method.invoke(null, new LeveledToolkit(standardToolkit, ToolkitLevel.MIDIUM));
				pieceCataModel.add(PieceCata.class.cast(object));

			}

		} catch (Exception e) {
			throw new LoadFailedException("读取试件类别处理器时失败。", e);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<LoadFailedException> countinuousLoad(ListModel<PieceCata> pieceCataModel) throws IllegalStateException {
		if (readFlag)
			throw new IllegalStateException("该读取器已经使用过了");

		Objects.requireNonNull(pieceCataModel, "入口参数 fileProcessorModel 不能为 null。");

		final Set<LoadFailedException> exceptions = new LinkedHashSet<>();
		try {
			readFlag = true;

			SAXReader reader = new SAXReader();
			Element root = reader.read(in).getRootElement();

			/*
			 * 根据 dom4j 的相关说明，此处转换是安全的。
			 */
			@SuppressWarnings("unchecked")
			List<Element> clazz = (List<Element>) root.elements("class");

			for (Element claz : clazz) {
				try {
					String nameString = claz.attributeValue("name");

					if (Objects.isNull(nameString)) {
						throw new IllegalArgumentException("不存在的xml字段 name。");
					}

					Method method = classLoader.loadClass(nameString).getMethod("newInstance", Toolkit.class);

					Object object = method.invoke(null, new LeveledToolkit(standardToolkit, ToolkitLevel.MIDIUM));
					pieceCataModel.add(PieceCata.class.cast(object));

				} catch (Exception e) {
					exceptions.add(new LoadFailedException("读取试件类别处理器时失败", e));
				}
			}

		} catch (Throwable e) {
			exceptions.add(new LoadFailedException("读取试件类别处理器时失败", e));
		}

		return exceptions;

	}

}
