fun main() : nothing
	fun foo(x : int) : int
	{
		x <- 2;
		return x + 2 - 3;
	}
	fun boo(y : int[10][20][30]; z : int[][100]) : int
	{
		if (z[0][0] # 0) then
			return y[0][1][2] + z[0][0];
	}
	fun choo(char_array : char[][2]) : char
	{
		char_array[0][1] <- 'a';
		$return;

		return char_array[0][1];
	}

{
	return;
}