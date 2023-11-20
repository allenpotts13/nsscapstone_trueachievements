package com.nashss.se.trueachievementsgroupservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.trueachievementsgroupservice.activity.requests.AddGameToGroupRequest;
import com.nashss.se.trueachievementsgroupservice.activity.results.AddGameToGroupResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;

public class AddGameToGroupLambda
    extends LambdaActivityRunner<AddGameToGroupRequest, AddGameToGroupResult>
    implements RequestHandler<AuthenticatedLambdaRequest<AddGameToGroupRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddGameToGroupRequest> input, Context context) {
        return super.runActivity(
            () -> {
                AddGameToGroupRequest unauthenticatedRequest = input.fromBody(AddGameToGroupRequest.class);
                String decodedGroupName = urlDecode(unauthenticatedRequest.getGroupName());
                return input.fromUserClaims(claims ->
                    AddGameToGroupRequest.builder()
                        .withUserId(claims.get("email"))
                        .withUniqueId(unauthenticatedRequest.getUniqueId())
                        .withGroupName(decodedGroupName)
                        .build());
            },
            (request, serviceComponent) ->
                serviceComponent.provideAddGameToGroupActivity().handleRequest(request)
        );
    }
    private String urlDecode(String input) {
        try {
            return java.net.URLDecoder.decode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("URL decoding error: " + e.getMessage(), e);
            return input;
        }
    }
}
