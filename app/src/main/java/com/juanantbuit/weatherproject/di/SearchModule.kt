package com.juanantbuit.weatherproject.di

import com.algolia.search.client.ClientSearch
import com.algolia.search.client.Index
import com.algolia.search.dsl.ranking
import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.settings.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SearchModule {

    @ViewModelScoped
    @Provides
    fun provideClientSearch(): ClientSearch {
        val algoliaAPIKey = "5efddf8a70f34495a8721556f6e991b"
        return ClientSearch(applicationID = ApplicationID("ZZEZP8MD9M"), APIKey(algoliaAPIKey))
    }

    @ViewModelScoped
    @Provides
    fun provideIndex(clientSearch: ClientSearch): Index {
        val algoliaIndexName = "locations"
        return clientSearch.initIndex(IndexName(algoliaIndexName))
    }

    @ViewModelScoped
    @Provides
    fun provideSettings(): Settings {
        return settings {
            searchableAttributes {
                +"name"
            }
            ranking {
                +Attribute
                +Geo
                +Words
                +Filters
                +Exact
            }
            hitsPerPage = 10
        }
    }
}