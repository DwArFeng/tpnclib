package com.dwarfeng.tpnclib.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.basic.io.SaveFailedException;
import com.dwarfeng.dutil.develop.cfg.DefaultExconfigModel;
import com.dwarfeng.dutil.develop.cfg.ExconfigModel;
import com.dwarfeng.dutil.develop.cfg.io.PropConfigSaver;
import com.dwarfeng.tpnclib.core.model.eum.CoreConfiguration;
import com.dwarfeng.tpnclib.core.model.eum.ModalConfiguration;

/**
 * 将程序的默认配置从 {@link CoreConfiguration} 和 {@link ModalConfiguration} 中写入资源中的配置文件。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
class ConfigurationSaver {

	public static void main(String[] args) throws IOException, SaveFailedException {
		ExconfigModel coreConfigModel = new DefaultExconfigModel(Arrays.asList(CoreConfiguration.values()));
		PropConfigSaver coreConfigSaver = null;
		try {
			coreConfigSaver = new PropConfigSaver(new FileOutputStream(new File(
					"src/core/com/dwarfeng/tpnclib/resource/core/defaultres/configuration/core.properties")));
			coreConfigSaver.save(coreConfigModel);
		} finally {
			if (Objects.nonNull(coreConfigSaver)) {
				coreConfigSaver.close();
			}
		}

		ExconfigModel modalConfigModel = new DefaultExconfigModel(Arrays.asList(ModalConfiguration.values()));
		PropConfigSaver modalConfigSaver = null;
		try {
			modalConfigSaver = new PropConfigSaver(new FileOutputStream(new File(
					"src/core/com/dwarfeng/tpnclib/resource/core/defaultres/configuration/modal.properties")));
			modalConfigSaver.save(modalConfigModel);
		} finally {
			if (Objects.nonNull(modalConfigSaver)) {
				modalConfigSaver.close();
			}
		}

		CT.trace("配置已经写入完毕!");

	}

}
