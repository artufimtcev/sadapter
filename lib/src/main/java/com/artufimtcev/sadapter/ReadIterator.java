package com.artufimtcev.sadapter;


import java.util.Iterator;


public class ReadIterator<E> implements Iterator<E> {

	private final Iterator<E> mIterator;


	public ReadIterator(Iterator<E> iterator) {
		mIterator = iterator;
	}


	@Override
	public boolean hasNext() {
		return mIterator.hasNext();
	}


	@Override
	public E next() {
		return mIterator.next();
	}


	@Override
	public final void remove() {
		throw new UnsupportedOperationException("Removing is not supported in ReadIterator");
	}
}
