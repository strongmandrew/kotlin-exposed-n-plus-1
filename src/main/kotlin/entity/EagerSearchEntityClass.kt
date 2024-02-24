package com.strongmandrew.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.*

open class EagerSearchEntityClass<ID : Comparable<ID>, out E : Entity<ID>>(table: IdTable<ID>) :
    EntityClass<ID, E>(table) {

    fun eagerFind(
        vararg foreignEntities: EntityClass<*, *>,
        columnSet: ColumnSet = dependsOnTables,
        op: SqlExpressionBuilder.() -> Op<Boolean>,
    ): SizedIterable<E> {
        warmCache()

        val entitiesBySearch = searchByColumnSet(columnSet, SqlExpressionBuilder.op()).map { resultRow ->
            foreignEntities.forEach { entity ->
                if (resultRow.getOrNull(entity.table.id) != null) {
                    entity.wrapRow(resultRow)
                }
            }
            this.wrapRow(resultRow)
        }

        return SizedCollection(entitiesBySearch)
    }

    open fun searchByColumnSet(columnSet: ColumnSet, op: Op<Boolean>): Query = columnSet.selectAll().where(op)
}