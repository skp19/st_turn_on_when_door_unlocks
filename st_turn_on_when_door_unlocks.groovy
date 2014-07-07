/**
 *  Turn On When Door Unlocks
 *
 *  Copyright 2014 skp19
 *
 */
definition(
    name: "Turn On When Door Unlocks",
    namespace: "skp19",
    author: "skp19",
    description: "Turns on a device when the door is unlocked",
    category: "Convenience",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section("When this door unlocks...") {
		input "lock1", "capability.lock", multiple: true
	}
    section("Turn these on...") {
    	input "switches", "capability.switch", multiple: true
    }
}

def installed() {
    subscribe(lock1, "lock", turniton)
}

def updated() {
	unsubscribe()
    subscribe(lock1, "lock", turniton)
}

def turniton(evt) {
    //log.debug "$evt.value: $evt, $settings"
    if(evt.value == "unlocked") {
		log.trace "Turning on switches: $switches"
        switches.on()
	}
}
