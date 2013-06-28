import scala.math._

/**
 * Created with IntelliJ IDEA.
 * User: rikki
 * Date: 26/06/13
 * Time: 16:17
 */
// Strictly speaking, this should be separated out into Point and Vector,
// since technically they are different, but I don't see the point (heheh).
class Vector(val x:Double, val y:Double) {

	def +(that:Vector) = Vector(this.x + that.x, this.y + that.y)
	def unary_- = Vector(-x, -y) // 'unary -' is the negation operator, e.g. v2 = -v1
	def -(that:Vector) = this + -that
	def *(factor:Double) = Vector(x * factor, y * factor)
	def /(factor:Double) = Vector(x / factor, y / factor)
	def dot(that:Vector) = this.x * that.x + this.y * that.y

	def magnitudeSquared = x*x + y*y
	def magnitude = sqrt(magnitudeSquared)
	def theta = atan2(x, y) // Theta component of (r,θ) polar coordinates

	def normalise = this / this.magnitude

	def distanceTo(that:Vector): Double = {
		val xDifference = this.x - that.x
		val yDifference = this.y - that.y
		Math.sqrt(xDifference*xDifference + yDifference*yDifference)
	}

	def rotateBy(angle:Double) = Vector.fromPolar(this.magnitude, this.theta + angle)

	def perpendicular = Vector(y, -x) // just some totally random perp. vector

	override def toString = "Vector(" + this.x + ", " + this.y + ")"
}

object Vector {
	val ZERO = Vector(0, 0)

	def apply(x:Double, y:Double) = new Vector(x, y) // allows us to just call 'Vector(x,y)' instead of 'new Vector(x,y)'
	def fromPolar(r:Double, θ:Double) = Vector(r * cos(θ), r * sin(θ))

	def midpointBetween(a:Vector, b:Vector) = (a + b) / 2
}