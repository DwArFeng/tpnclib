package com.dwarfeng.tpnclib.core.model.struct;

import java.util.List;

import javax.swing.text.StyledDocument;

import com.dwarfeng.dutil.basic.str.Name;

/**
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface InstrManager {

	/**
	 * 获取该试件类型的首页指导条目。
	 * 
	 * @return 该试件类型的首页指导条目，如果不希望指导文档出现在首页，返回 <code>null</code> 即可。
	 */
	public Name getFrontPageInstrItem();

	/**
	 * 获取该试件类型的所有指导条目。
	 * 
	 * <blockquote> 1.多次调用该方法，返回的映射关系不应发生变化。 <br>
	 * 2. 需要保证该列表是不可编辑的。<br>
	 * </blockquote>
	 * 
	 * @return 该试件类型所有的指导条目。
	 */
	public List<Name> getInstrItems();

	/**
	 * 获取该试件类型指定指导条目下的指导文档。
	 * 
	 * @param item
	 *            指定的指导条目。
	 * @return 该试件类型指定指导条目下的指导文档，如果条目不存在，返回 <code>null</code>即可。
	 */
	public StyledDocument newInstrDoc(Name item);

}
