$comment

fun main (argc : int; ref argv : char[][2]) : int
	fun main (par1 : char; ref par2 : int) : nothing;
	var x : int[10];
	var x1,x2,x3 : int;
	var y : char[2][5];
	var hanoi : int [5];
	fun main (par1 : char; ref par2 : int) : nothing
	var x1,x2,x3 : int;
	var y : int[2][5];
	var hanoi : int [5];
	{
	}


{

$$ multiple
line comment
comment comment
sdadada $$

x1 <- --++--++++2; 	

$$ another
multiple
comment

	x[2] <- x2 + 1;
	main('t', 2);
	x[2] <- hanoi[2];
	return x[0];
$$
		

}
