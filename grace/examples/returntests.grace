fun main() : nothing
	fun foo(x1,x2,x3 : int; z : char) : int
	var x : int;
	var y: int;
	var z2: char[2];
	{
		x <- 2;
		return x + 2 - 3;
	}
	fun boo(x1 : int[10][20][30]; z : int[][100]) : int
	var x : int;
	var y: int;
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