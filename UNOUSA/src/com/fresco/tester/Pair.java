package com.fresco.tester;

public class Pair<V, K> {

	V frist;
	K secoend;

	public Pair(V frist, K secoend) {
		super();
		this.frist = frist;
		this.secoend = secoend;
	}

	public V getFrist() {
		return frist;
	}

	public void setFrist(V frist) {
		this.frist = frist;
	}

	public K getSecoend() {
		return secoend;
	}

	public void setSecoend(K secoend) {
		this.secoend = secoend;
	}
}
