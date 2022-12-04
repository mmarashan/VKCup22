package io.volgadev.sampleapp.feature.dzentopicspicker.data

import io.volgadev.core.result.Result
import io.volgadev.sampleapp.feature.dzentopicspicker.domain.DzenTopicsRepository
import io.volgadev.sampleapp.feature.dzentopicspicker.domain.model.Topic

internal class DzenTopicsRepositoryImpl() : DzenTopicsRepository {

    override suspend fun loadTopics(): Result<List<Topic>, Throwable> {
        /**
         * Здесь мог быть запрос к бэку, но его нет :)
         */
        return Result.Success<List<Topic>>(
            listOf(
                Topic("0", "Юмор"),
                Topic("1", "Еда"),
                Topic("2", "Кино"),
                Topic("3", "Рестораны"),
                Topic("4", "Прогулки"),
                Topic("5", "Политика"),
                Topic("6", "Новости"),
                Topic("7", "Автомобили"),
                Topic("8", "Сериалы"),
                Topic("9", "Рецепты"),
                Topic("10", "Работа"),
                Topic("11", "Отдых"),
                Topic("12", "Спорт"),
                Topic("13", "IT"),
                Topic("14", "Мемы"),
                Topic("15", "Новости от VK"),
            )
        )
    }
}
