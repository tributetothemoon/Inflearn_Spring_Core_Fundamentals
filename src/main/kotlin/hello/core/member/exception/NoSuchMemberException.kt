package hello.core.member.exception

private const val message: String = "멤버를 찾을 수 없습니다."

class NoSuchMemberException : Throwable(message) {
/*
코틀린의 생성자 초기화 순서
주 생성자 (Primary Constructor) 호출
Init 블록 호출
보조 생성자 (Secondary Constructor) 호출
 */
}
