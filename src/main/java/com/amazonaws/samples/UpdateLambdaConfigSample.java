package com.amazonaws.samples;

import java.io.IOException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.Environment;
import com.amazonaws.services.lambda.model.UpdateFunctionConfigurationRequest;

public class UpdateLambdaConfigSample {

    public static void main(String[] args) throws IOException {
        try {
            String awsKey = args[0];
            String awsSecret = args[1];
            String functionName = args[2];

            AWSCredentials awsCredentials = new BasicAWSCredentials(awsKey, awsSecret);

            AWSLambda lambda = AWSLambdaClientBuilder
                    .standard()
                    .withRegion(Regions.US_WEST_2)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .build();
            lambda.updateFunctionConfiguration(
                    new UpdateFunctionConfigurationRequest()
                            .withEnvironment(
                                    new Environment()
                                            .addVariablesEntry("SOME_ENV_VAR", "someEnvVarValue")
                            ).withFunctionName(functionName)
            );
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with AWS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
}
