package physics;
/****************************************************************************
 * Copyright (C) 1999 by the Massachusetts Institute of Technology,
 *                           Cambridge, Massachusetts.
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
 * Author: Matt Frank, MIT Laboratory for Computer Science,
 *         mfrank@lcs.mit.edu
 *         1999-Apr-03
 *
 * Version: $Id: Angle.java,v 1.1 2002/08/21 21:49:40 kirky Exp $
 *
 ***************************************************************************/
import java.io.Serializable;

/**
 * Angle is an immutable abstract data type which represents the
 * mathematical notion of an angle.  <code>Angle</code> represents a
 * non-negative angle less than 360 degrees or 2*pi radians.
 **/public final class Angle
  implements Serializable, Comparable{

  // Rep. Invariant:
  //   cosine^2 + sine^2 = 1

  // Abstraction Function:
  //   The angle <a> such that cos(a) = cosine and sin(a) = sine

  // Rep. Rationale:
  //   In most of the math we use here we start in cartesian coordinates, so
  //   calculating sine and cosine is relatively efficient (just a sqrt and
  //   a division).  Finding the angle in terms of arcsin and arccos would be
  //   very slow.  On the other hand, adding and subtracting angles that are
  //   represented this way can be done relatively efficiently using standard
  //   trig. identities.

  private final double cosine;
  private final double sine;
    private static double EPSILON = 0.000001;
    
  private static final double SQRT = Math.sqrt(0.5);
  // useful constants
  /** A zero-degree or zero-radian angle */
  public static final physics.Angle ZERO = new physics.Angle(1.0, 0.0);
  /** A 45-degree angle */
  public static final physics.Angle DEG_45 = new physics.Angle(SQRT, SQRT);
  /** A 90-degree angle */
  public static final physics.Angle DEG_90 = new physics.Angle(0.0, 1.0);
  /** A 135-degree angle */
  public static final physics.Angle DEG_135 = new physics.Angle(-SQRT, SQRT);
  /** A 180-degree angle */
  public static final physics.Angle DEG_180 = new physics.Angle(-1.0, 0.0);
  /** A 225-degree angle */
  public static final physics.Angle DEG_225 = new physics.Angle(-SQRT, -SQRT);
  /** A 270-degree angle */
  public static final physics.Angle DEG_270 = new physics.Angle(0.0, -1.0);
  /** A 315-degree angle */
  public static final physics.Angle DEG_315 = new physics.Angle(SQRT, -SQRT);

  /** An angle of pi/4 radians */
  public static final physics.Angle RAD_PI_OVER_FOUR = DEG_45;
  /** An angle of pi/2 radians */
  public static final physics.Angle RAD_PI_OVER_TWO = DEG_90;
  /** An angle of pi radians */
  public static final physics.Angle RAD_PI = DEG_180;

  // CONSTRUCTORS:

  /**
   * @effects constructs an <code>Angle</code> with <code>radians</code> radians.
   */
  public Angle(double radians) {
    cosine = Math.cos(radians);
    sine = Math.sin(radians);
  }


    
  /**
   * @requires (x,y) != (0,0)
   *
   * @effects constructs the <code>Angle</code> that is formed between the
   * positive x-axis and the line from the origin to (<code>x</code>,
   * <code>y</code>).
   **/
  public Angle(double x, double y) {
    double r = Math.sqrt((x * x) + (y * y));
    if (r == 0.0) {
      if ((x == 0.0) && (y == 0.0)) {
	throw new IllegalArgumentException("Requires violated: Triangle is singular");
      } else {
	throw new ArithmeticException("Triangle is singular; imprecision on <" + x + "," + y + ">");
      }
    }
    cosine = x / r;
    sine = y / r;
  }

  // OBSERVERS:

  /**
   * @return the cosine of this.
   */
  public double cos() {
    return cosine;
  }

  /**
   * @return the sine of this.
   */
  public double sin() {
    return sine;
  }

  /**
   * @return the tangent of this.
   */
  public double tan() {
    return sine/cosine;
  }

  /**
   * @return the number of radians represented by this in the range of
   * -pi to pi.
   **/
  public double radians() {
    double d = Math.atan2(sine, cosine);
    if (d > Math.PI || d < -Math.PI) {
      System.out.println("d = " + d);
      throw new IllegalArgumentException();
    }
    return d;
  }

  /**
   * Compares this object with the specified object for order.
   * @return a negative integer, zero, or a positive integer as this
   * object is less than, equal to, or greater than the specified object.
   * @exception ClassCastException if <code>o</code> is not an Angle
   * @exception NullPointerException if <code>o</code> is null
   */
  public int compareTo(Object o)
  {
    // Comparable.compareTo allows us to throw a ClassCastException
    return compareTo((physics.Angle) o);
  }
  
  /**
   * Compares this object with the specified object for order.
   * @return a negative integer, zero, or a positive integer as this
   * object is less than, equal to, or greater than the specified object.
   * @exception NullPointerException if <code>c</code> is null
   */
  public int compareTo(physics.Angle c)
  {
    if (this.equals(c))
      return 0;

    // first discriminate on the basis of top vs. bottom half (sin)
    // then discriminate on the basis of left vs. right half (cos)

    if (sine >= 0.0) {
      if (c.sine < 0.0) {
	return -1;
      } else {
	if (cosine < c.cosine) {
	  return 1;
	} else {
	  return -1;
	}
      }
    } else {
      if (c.sine >= 0.0) {
	return 1;
      } else {
	if (cosine < c.cosine) {
	  return -1;
	} else {
	  return 1;
	}
      }
    }
  }

    public static void setEpsilon(double eps)
    {
	EPSILON = eps;
    }
	

  // PRODUCERS:

  /**
   * @requires <code>a</code> is not null
   * @return the angle <code>this</code> + <code>a</code>.
   */
  public physics.Angle plus(physics.Angle a) {
    // These are standard trig identities.  See the appendix of your
    // favorite calculus text book.
    double cosine = (this.cosine * a.cosine) - (this.sine * a.sine);
    double sine = (this.sine * a.cosine) + (this.cosine * a.sine);

    return new physics.Angle(cosine, sine);
  }

  /**
   * @requires <code>a</code> is not null
   * @return the angle <code>this</code> - <code>a</code>.
   */
  public physics.Angle minus(physics.Angle a) {
    // These are standard trig identities.  See the appendix of your
    // favorite calculus text book.
    double cosine = (this.cosine * a.cosine) + (this.sine * a.sine);
    double sine = (this.sine * a.cosine) - (this.cosine * a.sine);

    return new physics.Angle(cosine, sine);
  }

  public String toString() {
    return "Angle(" + cosine + "," + sine + ")";
  }

    
  public boolean equals(physics.Angle a) {
    if (a == null) return false;
    double cosDiff, sinDiff;
    cosDiff = this.cosine - a.cosine;
    sinDiff = this.sine - a.sine;
    return ((Math.abs(sinDiff)<=EPSILON) && (Math.abs(cosDiff)<=EPSILON));
  }

  public boolean equals(Object o) {
    return (o instanceof physics.Angle) && equals((physics.Angle) o);
  }

  public int hashCode() {
    return (new Double(sine)).hashCode() + (new Double(cosine)).hashCode();
  }

}
