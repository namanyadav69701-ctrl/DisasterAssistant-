package com.disasterassistant.app.data.local

object ProtocolSeedData {
    val protocols = listOf(
        EmergencyProtocolEntity(
            id = "SEVERE_BLEEDING",
            category = "SEVERE_BLEEDING",
            title = "Possible serious bleeding",
            summary = "Serious bleeding can require urgent professional medical care.",
            immediateActions = "Apply firm, continuous pressure using clean cloth or gauze.|Move away from immediate danger only if it is safe to do so.|Seek emergency medical assistance as soon as possible.",
            warnings = "Do not rely on this app as a replacement for professional medical care.|If the situation is worsening or you are unsure, seek emergency help.",
            escalationLevel = "CRITICAL",
            verificationStatus = "DEMO CONTENT — professional review required before real-world release"
        ),
        EmergencyProtocolEntity(
            id = "MINOR_BLEEDING",
            category = "MINOR_BLEEDING",
            title = "Possible minor bleeding",
            summary = "Use basic first-aid guidance and monitor for worsening symptoms.",
            immediateActions = "Use clean material to apply gentle pressure.|Keep the area clean.|Seek professional help if bleeding does not stop or the injury appears serious.",
            warnings = "Demo protocol only; professional review required.",
            escalationLevel = "MEDIUM",
            verificationStatus = "DEMO CONTENT — professional review required before real-world release"
        ),
        EmergencyProtocolEntity(
            id = "BURN",
            category = "BURN",
            title = "Possible burn",
            summary = "Move away from the source of danger and seek appropriate medical help.",
            immediateActions = "Move away from the source of heat or danger if safe.|Protect the affected area from further harm.|Seek medical assistance for serious or extensive burns.",
            warnings = "Do not use unverified home treatments.|Demo protocol only; professional review required.",
            escalationLevel = "HIGH",
            verificationStatus = "DEMO CONTENT — professional review required before real-world release"
        ),
        EmergencyProtocolEntity(
            id = "SUSPECTED_FRACTURE",
            category = "SUSPECTED_FRACTURE",
            title = "Possible fracture",
            summary = "Avoid unnecessary movement and seek professional medical assessment.",
            immediateActions = "Keep the person away from immediate danger if possible.|Avoid unnecessary movement of the injured area.|Seek professional medical assistance.",
            warnings = "Do not attempt to force the injured area back into position.|Demo protocol only.",
            escalationLevel = "HIGH",
            verificationStatus = "DEMO CONTENT — professional review required before real-world release"
        ),
        EmergencyProtocolEntity(
            id = "UNCONSCIOUS",
            category = "UNCONSCIOUS",
            title = "Unresponsive person",
            summary = "An unresponsive person may need urgent professional emergency care.",
            immediateActions = "Check that the surrounding area is safe.|Try to get emergency medical assistance immediately.|Follow instructions from trained emergency personnel if available.",
            warnings = "Do not delay professional emergency help.|Demo protocol only.",
            escalationLevel = "CRITICAL",
            verificationStatus = "DEMO CONTENT — professional review required before real-world release"
        ),
        EmergencyProtocolEntity(
            id = "DIFFICULTY_BREATHING",
            category = "DIFFICULTY_BREATHING",
            title = "Difficulty breathing",
            summary = "Breathing difficulty can be serious and needs prompt professional assessment.",
            immediateActions = "Move away from immediate environmental danger if safe.|Keep the person as calm as possible.|Seek emergency medical assistance promptly.",
            warnings = "Do not delay professional help if breathing is seriously impaired.|Demo protocol only.",
            escalationLevel = "CRITICAL",
            verificationStatus = "DEMO CONTENT — professional review required before real-world release"
        ),
        EmergencyProtocolEntity(
            id = "FLOOD",
            category = "FLOOD",
            title = "Flood emergency",
            summary = "Avoid known floodwater and move toward a lower-known-risk location when possible.",
            immediateActions = "Avoid entering moving or unknown-depth floodwater.|Move away from known hazards if a safer route is available.|Use stored offline map and hazard information cautiously.",
            warnings = "Offline hazard information can be outdated.|Absence of a stored hazard does not mean an area is safe.",
            escalationLevel = "HIGH",
            verificationStatus = "DEMO CONTENT — disaster-management review required"
        ),
        EmergencyProtocolEntity(
            id = "TRAPPED",
            category = "TRAPPED",
            title = "Trapped person",
            summary = "Prioritize immediate safety and attracting professional rescue assistance.",
            immediateActions = "Avoid actions that could make the surrounding structure or hazard less stable.|Use available safe ways to signal your location.|Seek professional rescue assistance when communication is possible.",
            warnings = "Do not take unnecessary risks attempting self-rescue.|Demo protocol only.",
            escalationLevel = "CRITICAL",
            verificationStatus = "DEMO CONTENT — professional review required"
        ),
        EmergencyProtocolEntity(
            id = "NEED_SHELTER",
            category = "NEED_SHELTER",
            title = "Need shelter",
            summary = "Use stored facility information to identify nearby shelters with lower known hazard exposure.",
            immediateActions = "Open the offline facilities view when available.|Compare known hazard exposure and route accessibility.|Prefer lower-known-risk options rather than assuming any location is safe.",
            warnings = "Offline facility and hazard data may be outdated.",
            escalationLevel = "UNKNOWN",
            verificationStatus = "DEMO CONTENT"
        ),
        EmergencyProtocolEntity(
            id = "NEED_MEDICAL_HELP",
            category = "NEED_MEDICAL_HELP",
            title = "Need medical help",
            summary = "Use stored hospital information and offline routing when available.",
            immediateActions = "Open the offline medical facilities view when available.|Compare route accessibility and known hazards.|Seek professional medical care as soon as it is reasonably possible.",
            warnings = "Offline map, facility, and hazard information may be outdated.",
            escalationLevel = "UNKNOWN",
            verificationStatus = "DEMO CONTENT"
        )
    )
}
