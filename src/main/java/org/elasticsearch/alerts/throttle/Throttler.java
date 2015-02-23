/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.alerts.throttle;

import org.elasticsearch.alerts.ExecutionContext;

/**
 *
 */
public interface Throttler {

    public static final Throttler NO_THROTTLE = new Throttler() {
        @Override
        public Result throttle(ExecutionContext ctx) {
            return Result.NO;
        }
    };

    Result throttle(ExecutionContext ctx);

    static class Result {

        public static final Result NO = new Result(false, null);
        
        private final boolean throttle;
        private final String reason;

        private Result(boolean throttle, String reason) {
            this.throttle = throttle;
            this.reason = reason;
        }

        public static Result throttle(String reason) {
            return new Result(true, reason);
        }

        public boolean throttle() {
            return throttle;
        }

        public String reason() {
            return reason;
        }

    }
}
