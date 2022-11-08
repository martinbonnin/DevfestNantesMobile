package com.gdgnantes.devfest.store.firestore

import com.gdgnantes.devfest.model.*
import com.gdgnantes.devfest.store.DevFestNantesStore
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

internal class FirebaseStore(private val firestore: FirebaseFirestore) : DevFestNantesStore {
    override val agenda: Flow<Agenda>
        get() = TODO("Not yet implemented")
    override val partners: Flow<Map<PartnerCategory, List<Partner>>>
        get() = TODO("Not yet implemented")

    override suspend fun getRoom(id: String): Room? {
        TODO("Not yet implemented")
    }

    override val rooms: Flow<Set<Room>>
        get() = TODO("Not yet implemented")

    override suspend fun getSession(id: String): Session? {
        TODO("Not yet implemented")
    }

    override val sessions: Flow<List<Session>>
        get() = TODO("Not yet implemented")

    override suspend fun getSpeaker(id: String): Speaker? {
        TODO("Not yet implemented")
    }

    override val speakers: Flow<List<Speaker>>
        get() = TODO("Not yet implemented")

    override suspend fun getVenue(language: ContentLanguage): Venue {
        firestore.collection("venues")
            .document(VENUE_ID)
            .toFlow()
    }

    companion object {
        private const val VENUE_ID = "CxVk0Br0ugnoKsZRXPtr"
    }
}