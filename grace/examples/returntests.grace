fun main() : nothing
	fun foo(x1,x2,x3 : int; z : char; x4 : int) : int
	var x : int;
	var z2: char[2];
	var y: int;
	{
		x <- 2;
		return x + 2 - 3;
	}
	fun boo(x1 : int[10][20][30]; z : int[][100]) : int
	var x : int[2][4];
	var y: int[5][10][20];
	var z2: char[2];
	{
		if (z[0][0] # 0) then
			return x1[0][1][2] + z[0][0];
	}
	fun choo(char_array : char[][2]) : char
	var x : int;
	var y: int;
	var z2: char[2];
	{
		char_array[0][1] <- 'a';
		$return;

		return char_array[0][1];
	}

{
	return;
}