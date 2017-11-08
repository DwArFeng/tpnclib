package com.dwarfeng.tpnclib.core.model.struct;

import java.awt.Image;
import java.util.Map;

import javax.swing.text.StyledDocument;

import com.dwarfeng.dutil.basic.str.Name;

/**
 * 试件类别接口。
 * 
 * <p>
 * 重要接口。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface PieceCata extends Name {

	/**
	 * 获取用作图标的图片。
	 * 
	 * @return 用作图标的图片。
	 */
	public Image getIconImage();

	/**
	 * 返回自定义代码是否可用。
	 * 
	 * @return 自定义代码是否可用。
	 */
	public boolean isCustomCodeAvaliable();

	/**
	 * 返回标准代码是否可用。
	 * 
	 * @return 标准代码是否可用。
	 */
	public boolean isStandardCodeAvaliable();

	/**
	 * 返回新的定制程序管理器。
	 * 
	 * <blockquote> 1.多次调用该方法，返回值应该在功能上相同。 <br>
	 * 2. 需要保证该映射是不可编辑的。<br>
	 * 3.该映射<b>一定要含有主键为 null 的元素</b>，作为默认的文档。</blockquote>
	 * 
	 * @return 新的定制程序管理器。
	 */
	public CustomCodeManager newCustomCodeManager();

	/**
	 * 返回新的试件指导文档。
	 * 
	 * <blockquote> 1.多次调用该方法，返回的映射关系不应发生变化。 <br>
	 * 2. 需要保证该映射是不可编辑的。<br>
	 * 3.该映射<b>一定要含有主键为 null 的元素</b>，作为默认的文档。</blockquote>
	 * 
	 * @return 新的试件指导文档。
	 */
	public Map<Name, StyledDocument> newInstructionDocuments();

	/**
	 * 返回新的标准程序管理器。
	 * 
	 * <blockquote> 1.多次调用该方法，返回值应该在功能上相同。 <br>
	 * 2. 需要保证该映射是不可编辑的。<br>
	 * 3.该映射<b>一定要含有主键为 null 的元素</b>，作为默认的文档。</blockquote>
	 * 
	 * @return 新的标准程序管理器。
	 */
	public StandardCodeManager newStandardCodeManager();

}
