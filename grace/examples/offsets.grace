fun main() : nothing

	fun foo(p26 : int; p25: char; p21: int[4][2]; p20: char; p16: int) : int; $just a declaration test
	var m4 : int;
	var m12 : int[2];
	var m92 : int[10][2];
	var m93 : char;
	var m96 : char[3][1];
	var m100 : int;
	var m580 : int[2][3][4][5];

	fun foo(p26 : int; p25: char; p21: int[4][2]; p20: char; p16: int) : int
	var fm4 : int;
	var fm6 : char[2];
	var fm26 : int[5];
	{
		$m12[m100] <- m92[2][m92[1][3]] + 5;
		m93 <- m96[1][0];
		m93 <- 'a';

		return fm4;
	}
	var fm30 : int;
	fun foo1(p16 : int) : int
	{
		return p16;
	}

{
	$		m580[1][2][3][4] <- 2;
	$		$m4 <- 2 + 3;
	$		$m4 <- m4 mod 2;

		m4 <- foo1(m4);
	$	m4 <- 2 + 4 * 5 + (foo(m4, 'c', m16, m96, m100));
}
