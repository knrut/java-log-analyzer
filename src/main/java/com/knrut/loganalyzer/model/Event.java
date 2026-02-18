package com.knrut.loganalyzer.model;

import java.time.LocalDateTime;
import java.util.Objects;

public sealed interface Event permits LoginEvent, LogoutEvent, PurchaseEvent, ErrorEvent {
    LocalDateTime timestamp();
    String userId();
}
