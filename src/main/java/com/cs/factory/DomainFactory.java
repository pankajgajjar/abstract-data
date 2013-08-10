package com.cs.factory;

import org.springframework.stereotype.Component;

import com.cs.data.core.GenericDomain;
import com.cs.model.ContentObject;

@Component
public class DomainFactory {

	public GenericDomain getDomainObject(String type) {
		if (type.equals("ContentObject"))
			return new ContentObject();

		return null;
	}

}
