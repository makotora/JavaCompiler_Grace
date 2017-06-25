fun main() : nothing
	var x : int;
	var y : int;
	var z : int;

	fun foo(x : int[]) : nothing
	{
		;
	}
{
	x <- 0;$should erase

	x <- 1; $no erase

	y <- x + 2;

	
}