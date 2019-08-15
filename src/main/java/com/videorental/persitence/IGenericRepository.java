package com.videorental.persitence;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericRepository<T, ID extends Serializable>
		extends CrudRepository<T, ID> {

//Not exposed CrudRepository methods:
//	
//	void deleteById(ID id);
//	
//	void delete(T entity);
//	
//	void deleteAll(Iterable<? extends T> entities);
//	
//	void deleteAll();

	<S extends T> S save(S entity);

	<S extends T> Iterable<S> saveAll(Iterable<S> entities);

	Optional<T> findById(ID id);

	boolean existsById(ID id);

	Iterable<T> findAll();

	long count();

	Iterable<T> findAllById(Iterable<ID> ids);

}
