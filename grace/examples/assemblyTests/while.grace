fun main() : nothing
var i : int;
var x : int[5];
{
	x[0] <- -1;
	x[1] <- -1;
	x[2] <- -1;
	x[3] <- -1;
	x[4] <- 1;
	i <- 0;
	while x[i] < 0 do
	{
		i <- i + 1;
	}
}