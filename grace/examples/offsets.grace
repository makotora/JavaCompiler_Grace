fun main() : nothing
	fun foo(p26 : int; p25: char; p21: int[4][2]; p20: char; p16: int) : int; $just a declaration test
	var m4 : int;
	var m8 : int[2];
	var m16 : int[10][2];
	var m96 : char;
	var m97 : char[3][1];
	var m100 : int;

	fun foo(p26 : int; p25: char; p21: int[4][2]; p20: char; p16: int) : int
	var fm4 : int;
	var fm8 : char[2];
	var fm10 : int[5];
	{
		fm4 <- 2 * 5 + - (3 + 3) + fm10[2] + m16[0][2];

		return fm4;
	}
	var x : int;

{
	m4 <- 2 + 4 * 5 + (foo(m4, 'c', m16, m96, m100));
}