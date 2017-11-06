package com.dwarfeng.tpnclib.core.model.struct;

import java.util.List;
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
public interface PieceCata {

	/**
	 * 返回试件指导文档。
	 * 
	 * <blockquote> 1.该返回值应为常量，不应发生变化。 <br>
	 * 2. 需要保证该映射是不可编辑的。<br>
	 * 3.该映射<b>一定要含有主键为 null 的元素</b>，作为默认的文档。</blockquote>
	 * 
	 * @return 试件指导文档。
	 */
	public Map<Name, StyledDocument> getInstructionDocuments();

	/**
	 * 获取该试件类别的根加工参数。
	 * 
	 * @return 该试件类别的根加工参数。
	 */
	public ProcessParam getRootProcessParam();

	/**
	 * 获取该试件类别接下来的加工参数。
	 * 
	 * @param processParams
	 *            已经确定的加工参数。
	 * @return 该试件类别接下来的加工参数。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public ProcessParam getNextProcessParam(List<ProcessParam> processParams);

	/**
	 * 获取指定的已确定加工参数能否生成NC代码。
	 * 
	 * @return 指定的已确定加工参数能否生成NC代码。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public boolean isNcCodeAvaliable(List<ProcessParam> processParams);

	/**
	 * 获取指定的已确定加工参数生成的NC代码。
	 * 
	 * @param processParams
	 *            指定的已确定加工参数生成的NC代码。
	 * @return 指定的已确定加工参数生成的NC代码。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public NcCode getNcCode(List<ProcessParam> processParams);

}
