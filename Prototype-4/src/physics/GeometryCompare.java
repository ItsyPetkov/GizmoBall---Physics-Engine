package physics;
import physics.Geometry.DoublePair;
import physics.Geometry.VectPair;
/****************************************************************************
 * Copyright (C) 2001 by the Massachusetts Institute of Technology,
 *                       Cambridge, Massachusetts.
 *
 *                        All Rights Reserved
 *
 * Permission to use, copy, modify, and distribute this software and
 * its documentation for any purpose and without fee is hereby
 * granted, provided that the above copyright notice appear in all
 * copies and that both that copyright notice and this permission
 * notice appear in supporting documentation, and that MIT's name not
 * be used in advertising or publicity pertaining to distribution of
 * the software without specific, written prior permission.
 *
 * THE MASSACHUSETTS INSTITUTE OF TECHNOLOGY DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE, INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS.  IN NO EVENT SHALL THE MASSACHUSETTS
 * INSTITUTE OF TECHNOLOGY BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
 * OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 *
 * @author: Jeremy Nimmer (jwnimmer@alum.mit.edu)
 *          Spring 2001
 *
 * Version: $Id: GeometryCompare.java,v 1.1 2002/08/21 21:49:40 kirky Exp $
 *
 ***************************************************************************/

/**
 * GeometryCompare is an implementation of the Geometry interface which composes
 * two other concrete implementations and checks that they both have
 * the same behavior.
 *
 * @see physics.Geometry
 **/public class GeometryCompare
  implements physics.GeometryInterface {

  /**
   * The tolerance which is used during comparison; results may differ
   * by this value at most.
   **/
  public static double TOLERANCE = 1E-12;

  /**
   * @requires g1 != null, g2 != null
   *
   * @effects returns a new GeometryCompare which dispatches to both
   * g1 and g2, throws an exception if the answers differ, and returns
   * the result from g1.
   **/
  public GeometryCompare(physics.GeometryInterface g1, physics.GeometryInterface g2)
  {
    if (g1 == null) throw new IllegalArgumentException("g1 null");
    if (g2 == null) throw new IllegalArgumentException("g2 null");
    this.g1 = g1;
    this.g2 = g2;
  }

  private final physics.GeometryInterface g1, g2;

  /**
   * A string thunk is a way of delaying the (expensive) creation of
   * Strings when they won't usually be used, anyway.  For example,
   * the failure message for an assertion is rarely used, so should
   * only be computed when the assertion fails.  (See section 4.2.2 in
   * SICP by Abelson, et. al.)
   **/
  private static interface StringThunk
  {
    /**
     * @return the forced value
     **/
    public String string();
  }

  /**
   * A simple empty-string thunk.
   **/
  private static final StringThunk NONE = new StringThunk() {
      public String string() { return ""; }
    };

  /**
   * A convenient way to prepend and/or append Strings to a StringThunk
   **/
  private class AddStringThunk
    implements StringThunk
  {
    public AddStringThunk(String before, StringThunk body, String after)
    {
      this.before = before;
      this.body = body;
      this.after = after;
    }
    private final String before;
    private final StringThunk body;
    private final String after;

    public String string()
    {
      return before + body.string() + after;
    }
  }

  // specification taken from GeometryInferface; not strengthened
  public DoublePair quadraticSolution(double a, double b, double c) {
    return compareAndReturn(NONE, g1.quadraticSolution(a, b, c),
			    g2.quadraticSolution(a, b, c));
  }

  // specification taken from GeometryInferface; not strengthened
  public double minQuadraticSolution(double a, double b, double c) {
    return compareAndReturn(NONE, g1.minQuadraticSolution(a, b, c),
			    g2.minQuadraticSolution(a, b, c));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Vect perpendicularPoint(physics.LineSegment line, physics.Vect point) {
    return compareAndReturn(NONE, g1.perpendicularPoint(line, point),
			    g2.perpendicularPoint(line, point));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Vect perpendicularPointWholeLine(physics.LineSegment line, physics.Vect point) {
    return compareAndReturn(NONE, g1.perpendicularPointWholeLine(line, point),
			    g2.perpendicularPointWholeLine(line, point));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Vect applyReflectionCoeff(physics.Vect incidentVect, physics.Vect reflectedVect, double rCoeff) {
    return compareAndReturn(NONE, g1.applyReflectionCoeff(incidentVect, reflectedVect, rCoeff),
			    g2.applyReflectionCoeff(incidentVect, reflectedVect, rCoeff));
  }

  // specification taken from GeometryInferface; not strengthened
  public double timeUntilWallCollision(physics.LineSegment line, physics.Circle ball, physics.Vect velocity) {
    return compareAndReturn(NONE, g1.timeUntilWallCollision(line, ball, velocity),
			    g2.timeUntilWallCollision(line, ball, velocity));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Vect reflectWall(physics.LineSegment line, physics.Vect velocity, double reflectionCoeff) {
    return compareAndReturn(NONE, g1.reflectWall(line, velocity, reflectionCoeff),
			    g2.reflectWall(line, velocity, reflectionCoeff));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Vect reflectWall(physics.LineSegment line, physics.Vect velocity) {
    return compareAndReturn(NONE, g1.reflectWall(line, velocity),
			    g2.reflectWall(line, velocity));
  }

  // specification taken from GeometryInferface; not strengthened
  public double distanceSquared(physics.Vect v1, physics.Vect v2) {
    return compareAndReturn(NONE, g1.distanceSquared(v1, v2),
			    g2.distanceSquared(v1, v2));
  }

  // specification taken from GeometryInferface; not strengthened
  public double distanceSquared(double x1, double y1, double x2, double y2) {
    return compareAndReturn(NONE, g1.distanceSquared(x1, y1, x2, y2),
			    g2.distanceSquared(x1, y1, x2, y2));
  }

  // specification taken from GeometryInferface; not strengthened
  public double timeUntilCircleCollision(physics.Circle circle, physics.Circle ball, physics.Vect velocity) {
    return compareAndReturn(NONE, g1.timeUntilCircleCollision(circle, ball, velocity),
			    g2.timeUntilCircleCollision(circle, ball, velocity));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Vect reflectCircle(physics.Vect circle, physics.Vect ball, physics.Vect velocity, double reflectionCoeff) {
    return compareAndReturn(NONE, g1.reflectCircle(circle, ball, velocity, reflectionCoeff),
			    g2.reflectCircle(circle, ball, velocity, reflectionCoeff));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Vect reflectCircle(physics.Vect circle, physics.Vect ball, physics.Vect velocity) {
    return compareAndReturn(NONE, g1.reflectCircle(circle, ball, velocity),
			    g2.reflectCircle(circle, ball, velocity));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Vect rotateAround(physics.Vect point, physics.Vect cor, physics.Angle a) {
    return compareAndReturn(NONE, g1.rotateAround(point, cor, a),
			    g2.rotateAround(point, cor, a));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.LineSegment rotateAround(physics.LineSegment line, physics.Vect cor, physics.Angle a) {
    return compareAndReturn(NONE, g1.rotateAround(line, cor, a),
			    g2.rotateAround(line, cor, a));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Circle rotateAround(physics.Circle circle, physics.Vect cor, physics.Angle a) {
    return compareAndReturn(NONE, g1.rotateAround(circle, cor, a),
			    g2.rotateAround(circle, cor, a));
  }

  // specification taken from GeometryInferface; not strengthened
  public DoublePair timeUntilCircleCollision(physics.Circle circle, physics.Vect point, physics.Vect velocity) {
    return compareAndReturn(NONE, g1.timeUntilCircleCollision(circle, point, velocity),
			    g2.timeUntilCircleCollision(circle, point, velocity));
  }

  // specification taken from GeometryInferface; not strengthened
  public double timeUntilRotatingWallCollision(final physics.LineSegment line,
					       final physics.Vect center,
					       final double angularVelocity,
					       final physics.Circle ball,
					       final physics.Vect velocity)
  {
    StringThunk message = new StringThunk() {
	public String string() {
	  return
	    "timeUntilRotatingWallCollision(" +
	    line +
	    " " +
	    center +
	    " " +
	    angularVelocity +
	    " " +
	    ball +
	    " " + 
	    velocity + 
	    ")";
	}
      };
    return compareAndReturn(message,
			    g1.timeUntilRotatingWallCollision(line, center, angularVelocity, ball, velocity),
			    g2.timeUntilRotatingWallCollision(line, center, angularVelocity, ball, velocity));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Vect reflectRotatingWall(physics.LineSegment line, physics.Vect center, double angularVelocity, physics.Circle ball, physics.Vect velocity) {
    return compareAndReturn(NONE, g1.reflectRotatingWall(line, center, angularVelocity, ball, velocity),
			    g2.reflectRotatingWall(line, center, angularVelocity, ball, velocity));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Vect reflectRotatingWall(physics.LineSegment line, physics.Vect center, double angularVelocity, physics.Circle ball, physics.Vect velocity, double reflectionCoeff) {
    return compareAndReturn(NONE, g1.reflectRotatingWall(line, center, angularVelocity, ball, velocity, reflectionCoeff),
			    g2.reflectRotatingWall(line, center, angularVelocity, ball, velocity, reflectionCoeff));
  }

  // specification taken from GeometryInferface; not strengthened
  public double timeUntilRotatingCircleCollision(physics.Circle circle, physics.Vect center, double angularVelocity, physics.Circle ball, physics.Vect velocity) {
    return compareAndReturn(NONE, g1.timeUntilRotatingCircleCollision(circle, center, angularVelocity, ball, velocity),
			    g2.timeUntilRotatingCircleCollision(circle, center, angularVelocity, ball, velocity));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Vect reflectRotatingCircle(physics.Circle circle, physics.Vect center, double angularVelocity, physics.Circle ball, physics.Vect velocity) {
    return compareAndReturn(NONE, g1.reflectRotatingCircle(circle, center, angularVelocity, ball, velocity),
			    g2.reflectRotatingCircle(circle, center, angularVelocity, ball, velocity));
  }

  // specification taken from GeometryInferface; not strengthened
  public physics.Vect reflectRotatingCircle(physics.Circle circle, physics.Vect center, double angularVelocity, physics.Circle ball, physics.Vect velocity, double reflectionCoeff) {
    return compareAndReturn(NONE, g1.reflectRotatingCircle(circle, center, angularVelocity, ball, velocity, reflectionCoeff),
			    g2.reflectRotatingCircle(circle, center, angularVelocity, ball, velocity, reflectionCoeff));
  }

  // specification taken from GeometryInferface; not strengthened
  public double timeUntilBallBallCollision(physics.Circle ball1, physics.Vect vel1, physics.Circle ball2, physics.Vect vel2) {
    return compareAndReturn(NONE, g1.timeUntilBallBallCollision(ball1, vel1, ball2, vel2),
			    g2.timeUntilBallBallCollision(ball1, vel1, ball2, vel2));
  }

  // specification taken from GeometryInferface; not strengthened
  public VectPair reflectBalls(physics.Vect center1, double mass1, physics.Vect velocity1, physics.Vect center2, double mass2, physics.Vect velocity2) {
    return compareAndReturn(NONE, g1.reflectBalls(center1, mass1, velocity1, center2, mass2, velocity2),
			    g2.reflectBalls(center1, mass1, velocity1, center2, mass2, velocity2));
  }

  /**
   * @param message a thunk for the message
   * @param a first value
   * @param b second value
   *
   * @throws a RuntimeException whose message includes information
   *         about the two values, their difference, and the provided
   *         message
   **/
  private static void error(StringThunk message, double a, double b)
  {
    throw new RuntimeException("Assertion error: " + a + " != " + b + "; diff = " + (a - b) + "\n" +
			       message.string());
  }

  /**
   * @effects does nothing if a and b are equal within the given tolerance
   * @throws RuntimeException if the values are not within tolerance
   * @see #TOLERANCE
   **/
  private static void assertEquals(StringThunk message, double a, double b)
  {
    if (Double.isNaN(a)) {
      if (Double.isNaN(b)) {
	return;
      } else {
	error(message, a, b);
      }
    }
    if (Double.isInfinite(a)) {
      if (Double.isInfinite(b)) {
	return;
      } else {
	error(message, a, b);
      }
    }
    double diff = Math.abs(a - b);
    if (diff < TOLERANCE) {
      return;
    } else {
      error(message, a, b);
    }
  }

  /**
   * @effects returns a if a and b are within tolerance of each other,
   * else throws a RuntimeException
   **/
  private static double compareAndReturn(StringThunk message, double a, double b)
  {
    assertEquals(message, a, b);
    return a;
  }

  /**
   * @effects returns a if the component of a and b are within
   * tolerance of each other, else throws a RuntimeException
   **/
  private static DoublePair compareAndReturn(StringThunk message, DoublePair a, DoublePair b)
  {
    assertEquals(message, a.d1, b.d1);
    assertEquals(message, a.d2, b.d2);
    return a;
  }

  /**
   * @effects returns a if the component of a and b are within
   * tolerance of each other, else throws a RuntimeException
   **/
  private static physics.Vect compareAndReturn(StringThunk message, physics.Vect a, physics.Vect b)
  {
    assertEquals(message, a.x(), b.x());
    assertEquals(message, a.x(), b.x());
    return a;
  }

  /**
   * @effects returns a if the component of a and b are within
   * tolerance of each other, else throws a RuntimeException
   **/
  private static VectPair compareAndReturn(StringThunk message, VectPair a, VectPair b)
  {
    compareAndReturn(message, a.v1, b.v1);
    compareAndReturn(message, a.v2, b.v2);
    return a;
  }

  /**
   * @effects returns a if the component of a and b are within
   * tolerance of each other, else throws a RuntimeException
   **/
  private static physics.Circle compareAndReturn(StringThunk message, physics.Circle a, physics.Circle b)
  {
    compareAndReturn(message, a.getCenter(), b.getCenter());
    compareAndReturn(message, a.getRadius(), b.getRadius());
    return a;
  }

  /**
   * @effects returns a if the component of a and b are within
   * tolerance of each other, else throws a RuntimeException
   **/
  private static physics.LineSegment compareAndReturn(StringThunk message, physics.LineSegment a, physics.LineSegment b)
  {
    compareAndReturn(message, a.p1(), b.p1());
    compareAndReturn(message, a.p2(), b.p2());
    return a;
  }

}