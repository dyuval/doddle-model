package com.picnicml.doddlemodel.data

import com.picnicml.doddlemodel.TestUtils
import org.scalactic.{Equality, TolerantNumerics}
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class DataUtilsTest extends FlatSpec with Matchers with TestUtils {

  implicit val rand: Random = new Random(0)
  implicit val doubleTolerance: Equality[Double] = TolerantNumerics.tolerantDoubleEquality(1.0)

  val (x, y) = loadIrisDataset

  "Data utils" should "shuffle the dataset correctly" in {
    val (_, yShuffled) = shuffleDataset(x, y)
    breezeEqual(y, yShuffled) shouldBe false
  }

  they should "split the dataset correctly" in {
    val (_, yTr, _, yTe) = splitDataset(x, y)
    yTr.length shouldBe 75
    yTe.length shouldBe 75
  }
}
