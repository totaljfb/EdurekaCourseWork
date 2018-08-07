package module5.generics;

public class FriendshipCriteria<T extends Comparable<? super T>, S extends Comparable<? super S>> implements Comparable<FriendshipCriteria<T,S>>{
	
	private T t;
	public T getT() {
		return t;
	}
	public S getS() {
		return s;
	}
	private S s;
	//constructor
	FriendshipCriteria(T t, S s){
		this.t = t;
		this.s = s;	
	}
	//return 0 if both s, and t equal o.s and o.t respectively
	@Override
	public int compareTo(FriendshipCriteria<T, S> o) {
		if(t.compareTo(o.t) == 0 && s.compareTo(o.s) == 0) {
			return 0;
		}
		else{
			return 1;
		}
	}
}
