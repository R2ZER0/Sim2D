/**
 * Created with IntelliJ IDEA.
 * User: rikki
 * Date: 26/06/13
 * Time: 17:06
 */
class Line(val position:Vector, val direction:Vector) {

	def distanceTo(point:Vector):Double = {
		// Gotta love wikipedia
		// dist(X = A + tN, P) = ||(A-P)-((A-P).N)N||
		// How did i forget linear algebra so quickly?!
		// Here a = position, n = direction, p = point
		((position - point) - (direction * ((position - point) dot direction))).magnitude
	}

}

object Line {
	def apply(position:Vector, direction:Vector) = new Line(position, direction)
}
