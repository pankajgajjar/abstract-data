package com.cs.data.core.file;

import java.util.List;

import com.cs.data.core.GenericDomain;
import com.cs.data.core.IRepository;

public interface FileOperations extends IRepository {

	String save(GenericDomain objectToInsert);

	<P> P getObjectByKey(GenericDomain key, Class<P> type);

	List<GenericDomain> getAll();

}
