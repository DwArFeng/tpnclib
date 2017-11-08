package com.dwarfeng.tpnclib.core.model.eum;

import com.dwarfeng.dutil.basic.str.Name;
import com.dwarfeng.tpnclib.core.util.Constants;

/**
 * 程序中的图片键。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public enum ImageKey implements Name {

	/** 首页文档的标题 */
	FROUNTPAGE_DOCUMENT_TITLE("frontpage_document_title.png"),

	;

	private String name;

	private ImageKey(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return Constants.RESOURCE_IMAGE_ROOT_PATH + name;
	}

}
