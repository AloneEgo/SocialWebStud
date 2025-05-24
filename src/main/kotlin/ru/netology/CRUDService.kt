package ru.netology

abstract class CRUDService<T : Item> {
    protected val items = mutableListOf<T>()
    protected var nextID: Int = 1

    fun getIndexById(id: Int): Int {
        for ((index, eachItem) in items.withIndex()) {
            if (eachItem.id == id) return index
        }

        throw TargetNotFoundException("Запись не найдена")
    }

    fun create(item: T): Int {
        items += item
        return item.id
    }

    fun read(id: Int): T {

        for (item in items) {
            if (item.id == id) {
                if (!item.isDeleted) {
                    return item
                } else throw TargetNotFoundException("Запись удалена")
            }
        }

        throw TargetNotFoundException("Запись не найдена")

    }

    fun update(item: T): Boolean {

        val targetId = item.id
        val index = getIndexById(targetId)

        if (!items[index].isDeleted) {
            items[index] = item
            return true
        } else {
            throw TargetNotFoundException("Запись удалена")
        }

    }

    fun delete(id: Int, soft: Boolean): Boolean {

        val index = getIndexById(id)

        if (!items[index].isDeleted) {
            if (soft) {
                items[index].isDeleted = true
            } else {
                items.removeAt(index)
            }
            return true
        } else {
            throw TargetNotFoundException("Запись уже удалена")
        }

    }

    fun restore(id: Int): Boolean {

        val index = getIndexById(id)

        if (items[index].isDeleted) {
            items[index].isDeleted = false
            return true
        }
        throw TargetNotFoundException("Запись не удалена")
    }
}
