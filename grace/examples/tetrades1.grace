$comment

fun main (argc : int; ref argv : char[][2]) : int
	fun main (par1 : char; ref par2 : int) : nothing;
	var x : int[10][10];
	var array : int[2];
	var x1,x2,x3 : int;
	var y : char[2][5];
	var hanoi : int [5];
	fun main (par1 : char; ref par2 : int) : nothing
	var x1,x2,x3 : int;
	var y : int[2][5];
	var hanoi : int [5];
	{
	}
	fun add_function(x :int; y :int) : int
	{
		$return x+y;
	}

{

$$ multiple
line comment
comment comment
sdadada $$

x[x[1][2]][3] <- 5 * x[1][2] + x2 mod array[2];
main('c', x1);
main('c', x[1][2]);
$x1 <- --++--++++2;
$x2 <- 5 * (2 - 3) mod 2 + add_function(2, 3);

$The two following are wrong 	
$x2 <- - main('c', 2);
$y[0] <- - "string";
$y[0] <- - "string"[0];

$$ another
multiple
comment
	x[2] <- x2 + 1;
	main('t', 2);
	x[2] <- hanoi[2];
	return x[0];
$$
		

}
