/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.activiti.services.rest.resources.assembler;

import org.activiti.services.core.model.ProcessInstance;
import org.activiti.services.rest.resources.ProcessInstanceResource;
import org.activiti.services.rest.controllers.HomeController;
import org.activiti.services.rest.controllers.ProcessInstanceController;
import org.activiti.services.rest.controllers.ProcessInstanceVariableController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ProcessInstanceResourceAssembler extends ResourceAssemblerSupport<ProcessInstance, ProcessInstanceResource> {

    public ProcessInstanceResourceAssembler() {
        super(ProcessInstanceController.class, ProcessInstanceResource.class);
    }

    @Override
    public ProcessInstanceResource toResource(ProcessInstance processInstance) {
        Link processInstancesRel = linkTo(methodOn(ProcessInstanceController.class).getProcessInstances(null, null)).withRel("processInstances");
        Link selfLink = linkTo(methodOn(ProcessInstanceController.class).getProcessInstanceById(processInstance.getId())).withSelfRel();
        Link variablesLink = linkTo(methodOn(ProcessInstanceVariableController.class).getVariables(processInstance.getId())).withRel("variables");
        Link homeLink = linkTo(HomeController.class).withRel("home");
        return new ProcessInstanceResource(processInstance, selfLink, variablesLink, processInstancesRel, homeLink);
    }

}
