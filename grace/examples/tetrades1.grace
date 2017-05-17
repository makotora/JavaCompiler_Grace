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
		x1 <- 2;
	}
	fun add_function(x :int; y :int) : int
	{
		return x+y;
	}
	fun x5(): int
	{
		return 5;
	}

{

$$ multiple
line comment
comment comment
sdadada $$

if not 1 # x5() then
{

x1 <- 5;		
x1 <- 5;		
x1 <- 5;		
}
else
{
	x2 <- 6;
}
}
