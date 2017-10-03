package com.lz.retailApi.dealer.model.entity

import com.lz.stationApi.dealer.model.entity.DealerType
import org.scalatest.FunSpec

class DealerSpec extends FunSpec {
  describe("A dealer type") {
    describe("when initialized") {
      it("should have the expected type") {
        val valTypeA = 1
        val typeA = DealerType.fromType(valTypeA)
        assert(typeA.isSuccess)
        assert(typeA.getOrElse(-1) == valTypeA)

        val valTypeB = 2
        val typeB = DealerType.fromType(valTypeB)
        assert(typeB.isSuccess)
        assert(typeB.getOrElse(-1) == valTypeB)
      }

      it("should fails if the given type is unknown") {
        assert(DealerType.fromType(31).isFailure)
      }
    }
  }
}
