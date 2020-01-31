/*
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.kkb.temperature.constant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 简单分页模型
 * </p>
 *
 * @author hubin
 * @since 2018-06-09
 */
public class KkbPage<T> extends Page<T> {


	private static final long serialVersionUID = 8545996863226528798L;

	private Map<Object, Object> condition = new HashMap<>();

	private String[] ascs;

	private String[] descs;

	public Map<Object, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<Object, Object> condition) {
		this.condition = condition;
	}

	public void addCondition(String key, Object value) {
		getCondition().put(key, value);
	}

	public String[] getAscs() {
		return ascs;
	}

	public void setAscs(String[] ascs) {
		this.ascs = ascs;
		if (ascs != null && ascs.length > 0) {
			super.setAsc(ascs);
		}
	}

	public String[] getDescs() {
		return descs;
	}

	public void setDescs(String[] descs) {
		this.descs = descs;
		if (descs != null && descs.length > 0) {
			super.setDesc(descs);
		}
	}
}
