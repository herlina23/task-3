package task3.models

import org.joda.time.DateTime

case class CleanStream(
    originalId: String,
    content: String,
    timestamp: DateTime
)

// list of cleanstream
