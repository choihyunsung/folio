package kr.block.common.object;

/***
 * @TODO HSCHOE 페어를 임시로 만들어서 사용 나중에 자료참고해서 다시 만들어낼 필요 있음.
 * @author choehyeonseong
 *
 * @param <First> 첫번째 값.
 * @param <Second> 두번째 값.
 */
public class Pair<First, Second> {
	private First first;
	private Second second;
	
	public Pair(First first, Second second) {
		super();
		this.first = first;
		this.second = second;
	}

	public First getFirst() {
		return first;
	}

	public Second getSecond() {
		return second;
	}
}
